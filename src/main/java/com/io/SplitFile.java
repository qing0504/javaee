package com.io;

import com.common.utils.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 分割\合并文件对象
 *
 * @author wanchongyang
 */
public class SplitFile {
    //文件的路径
    private String filePath;
    //文件名称
    private String fileName;
    //文件大小
    private long length;
    //分割块数
    private int size;
    //每块的大小
    private long blockSize;
    //每块的名称
    private List<String> blockPath;

    public SplitFile() {
        blockPath = new ArrayList<>();
    }

    public SplitFile(String filePath) {
        //初始化默认大小0.5M
        this(filePath, 1024*512);
    }

    public SplitFile(String filePath, long blockSize) {
        this();
        this.filePath = filePath;
        this.blockSize = blockSize;
        init();
    }

    /**
     * 初始化操作
     * 计算块数，确定文件名
     */
    public void init() {
        File src = null;
        //健壮性
        if (this.filePath == null || !((src = new File(this.filePath)).exists())){
            return;
        }

        //文件夹判断
        if (src.isDirectory()) {
            return;
        }
        //文件名称
        this.fileName = src.getName();

        //计算块数 实际大小与每块大小
        this.length = src.length();
        //修正每块大小
        if (this.blockSize > this.length) {
            this.blockSize = this.length;
        }

        //确定块数（向上取整）
        size = (int) Math.ceil(length * 1.0 / this.blockSize);
    }

    /**
     * 文件的分割
     *
     * @param destPath 分割文件存放目录
     */
    public void split(String destPath) {
        //确定文件路径
        initPathName(destPath);

        long beginPos = 0; //起始点
        long actualBlockSize = this.blockSize; //实际大小

        //计算所有块的大小、位置、索引
        for (int j = 0; j < this.size; j++) {
            if (j == this.size - 1) {//最后一块
                actualBlockSize = this.length - beginPos;
            }

            splitDetail(j, beginPos, actualBlockSize);
            beginPos += actualBlockSize;//本次的终点，下一次的起点
        }

    }

    /**
     * 文件的分割具体操作 输入、输出
     * @param idx 第几块
     * @param beginPos 起始位置
     * @param actualBlockSize 实际大小
     */
    private void splitDetail(int idx, long beginPos, long actualBlockSize){
        //1、创建源
        File src = new File(this.filePath); //源文件
        File dest = new File(this.blockPath.get(idx)); //目标文件
        //2、选择流
        RandomAccessFile raf = null; //输入流
        BufferedOutputStream bos = null; //输出流
        try {
            raf = new RandomAccessFile(src, "r");
            bos = new BufferedOutputStream(new FileOutputStream(dest));

            //读取文件
            raf.seek(beginPos);
            //缓冲区
            byte[] flush = new byte[1024];
            //接收长度
            int len = 0;
            while (-1 != (len = raf.read(flush))) {
                if (actualBlockSize - len >= 0) {//查看是否足够
                    //写出
                    bos.write(flush, 0, len);
                    actualBlockSize -= len;
                } else {
                    //写出最后剩余量
                    bos.write(flush, 0, (int) actualBlockSize);
                    break;
                }
            }

            bos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                FileUtils.closeAll("文件流关闭异常", bos, raf);
            }
        }

    }

    /**
     *
     * @param destPath 文件路径
     */
    private void initPathName(String destPath) {
        for (int i = 0; i < this.size; i++) {
            this.blockPath.add(destPath + "/"+ this.fileName + ".part" + (i + 1));
        }
    }

    /**
     * 文件的合并
     * @param destPath 存储文件路径
     */
    public void mergeFile(String destPath) {
        //创建源
        File dest = new File(destPath);
        //选择流
        BufferedOutputStream bos = null; //输出流
        try {
            bos = new BufferedOutputStream(new FileOutputStream(dest, true));//追加
            BufferedInputStream bis = null;//输入流
            for (int i = 0; i < this.size; i++) {
                bis = new BufferedInputStream(new FileInputStream(this.blockPath.get(i)));
                //缓冲区
                byte[] flush = new byte[1024];
                //接收长度
                int len = 0;
                while (-1 != (len = bis.read(flush))) {
                    bos.write(flush, 0, len);
                }
            }

            bos.flush();
            FileUtils.close(bis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(bos);
        }
    }

    /**
     * 文件的合并，使用SequenceInputStream
     * @param destPath 存储文件路径
     */
    public void merge(String destPath) {
        //创建源
        File dest = new File(destPath);
        //选择流
        BufferedOutputStream bos = null; //输出流
        //创建一个容器
        Vector<InputStream> vi = new Vector<>();
        try {
            bos = new BufferedOutputStream(new FileOutputStream(dest, true));//追加
            for (int i = 0; i < this.size; i++) {
                vi.add(new BufferedInputStream(new FileInputStream(this.blockPath.get(i))));
            }
            //合并流
            SequenceInputStream sis = new SequenceInputStream(vi.elements());
            //缓冲区
            byte[] flush = new byte[1024];
            //接收长度
            int len = 0;
            while (-1 != (len = sis.read(flush))) {
                bos.write(flush, 0, len);
            }

            bos.flush();
            FileUtils.close(sis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtils.close(bos);
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(long blockSize) {
        this.blockSize = blockSize;
    }

    public List<String> getBlockPath() {
        return blockPath;
    }

    public void setBlockPath(List<String> blockPath) {
        this.blockPath = blockPath;
    }
}

