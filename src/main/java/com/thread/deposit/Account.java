package com.thread.deposit;

/**
 * 银行账户
 * @author wanchongyang
 * <b>DATE</b> 2016年2月17日 下午4:38:05
 */
public class Account {
	private double balance;
	/*haveBalance标识当前账户是否还有余额*/
    private boolean haveBalance = false;

    public Account() {
    }

    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void reduceBalance(double count) {
        this.balance -= count;
    }
    
//    public synchronized boolean draw(double money) {
//        if (getBalance() > money) {
//            System.out.println(Thread.currentThread().getName() + "取钱" + money + "成功");
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            balance -= money;
//            System.out.println("\t" + Thread.currentThread().getName() + "成功后的余额: " + getBalance());
//            return true;
//        } else {
//            System.out.println(Thread.currentThread().getName() + "取钱失败");
//            System.out.println("\t" + Thread.currentThread().getName() + "失败后的余额: " + getBalance());
//            return false;
//        }
//    }
    
    /**
     * 取钱
     *
     * @param amount
     */
    public synchronized void draw(double amount) throws InterruptedException {
        // 如果没有存款, 则释放锁定, 持续等待
        while (!haveBalance) {
            wait();
        }

        System.out.printf("%s执行取钱操作", Thread.currentThread().getName());
        balance -= amount;
        System.out.printf(", 当前余额%f%n", balance);
        haveBalance = false;

        // 唤醒其他线程
        notifyAll();
    }

    /**
     * 存钱
     *
     * @param amount
     */
    public synchronized void deposit(double amount) throws InterruptedException {
        // 如果有存款, 则释放锁定, 持续等待
        while (haveBalance) {
            wait();
        }

        System.out.printf("%s执行存钱操作", Thread.currentThread().getName());
        balance += amount;
        System.out.printf(", 当前余额%f%n", balance);
        haveBalance = true;

        // 唤醒其他线程
        notifyAll();
    }
}
