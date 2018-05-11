package com.thread.executor;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture：https://www.jianshu.com/p/6f3ee90ab7d3
 *
 * @author wanchongyang
 * @date 2018/5/11 上午9:41
 */
public class CompletableFutureDemo2 {
    public static void main(String[] args) {
        // 1、进行变换
        // thenApply();

        // 2、进行消耗
        // thenAccept();

        // 3、对上一步的计算结果不关心，执行下一个操作
        // thenRun();

        // 4、结合两个CompletionStage的结果，进行转化后返回
        // thenCombine();

        // 5、结合两个CompletionStage的结果，进行消耗
        // thenAcceptBoth();

        // 6、在两个CompletionStage都运行完执行
        // runAfterBoth();

        // 7、两个CompletionStage，谁计算的快，我就用那个CompletionStage的结果进行下一步的转化操作
        // applyToEither();

        // 8、两个CompletionStage，谁计算的快，我就用那个CompletionStage的结果进行下一步的消耗操作。
        // acceptEither();

        // 9、两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
        // runAfterEither();

        // 10、当运行时出现了异常，可以通过exceptionally进行补偿。
        // exceptionally();

        // 11、当运行完成时，对结果的记录。这里的完成时有两种情况，一种是正常执行，返回值。另外一种是遇到异常抛出造成程序的中断。
        // whenComplete();

        // 12、运行完成时，对结果的处理。这里的完成时有两种情况，一种是正常执行，返回值。另外一种是遇到异常抛出造成程序的中断。
        handle();
    }

    private static void handle() {
        System.out.println("==============handle==================");
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 异常
            int num = 1 / 0;

            return "s1";
        }).handle((s, t) -> {
            if (t != null) {
                return t.getMessage();
            }

            return s;
        }).join();

        System.out.println(result);
    }

    private static void whenComplete() {
        System.out.println("==============exceptionally==================");
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int num = 1 / 0;

            return "s1";
        }).whenComplete((s, t) -> {
            System.out.println("whenComplete:" + s);
            System.out.println("whenComplete:" + t.getMessage());
        }).exceptionally(e -> {
            System.out.println("exceptionally:" + e.getMessage());
            return "hello world";
        }).join();

        System.out.println(result);
    }

    private static void exceptionally() {
        System.out.println("==============exceptionally==================");
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int num = 1 / 0;

            return "s1";
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "hello world";
        }).join();

        System.out.println(result);
    }

    private static void runAfterEither() {
        System.out.println("==============runAfterEither==================");
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("first CompletableFuture isDone.");
            return "s1";
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("second CompletableFuture isDone.");
            return "s2";
        }), () -> System.out.println("hello world"));

        while (true) {
            if (completableFuture.isDone()) {
                break;
            }
        }
    }

    private static void acceptEither() {
        System.out.println("==============acceptEither==================");
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "s1";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "hello world";
        }), System.out::println);

        while (true) {
            if (completableFuture.isDone()) {
                break;
            }
        }
    }

    private static void applyToEither() {
        System.out.println("==============runAfterBoth==================");
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "hello world";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "s1";
        }), s -> s).join();

        System.out.println(result);
    }

    private static void runAfterBoth() {
        System.out.println("==============runAfterBoth==================");
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("first CompletableFuture isDone.");
            return "hello";
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("second CompletableFuture isDone.");
            return "world";
        }), () -> System.out.println("hello world"));

        while (true) {
            if (completableFuture.isDone()) {
                break;
            }
        }
    }

    private static void thenAcceptBoth() {
        System.out.println("==============thenAcceptBoth==================");
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("first CompletableFuture isDone.");
            return "hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("second CompletableFuture isDone.");
            return "world";
        }), (s1, s2) -> System.out.println(s1 + " " + s2));

        while (true) {
            if (completableFuture.isDone()) {
                break;
            }
        }
    }

    private static void thenCombine() {
        System.out.println("==============thenCombine==================");
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("first CompletableFuture isDone.");
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("second CompletableFuture isDone.");
            return "world";
        }), (s1, s2) -> s1 + " " + s2).join();

        System.out.println(result);
    }

    private static void thenRun() {
        System.out.println("==============thenRun==================");
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "hello";
        }).thenRun(() -> System.out.println("hello world"));

        while (true) {
            if (completableFuture.isDone()) {
                break;
            }
        }
    }

    private static void thenAccept() {
        System.out.println("==============thenAccept==================");
        CompletableFuture.supplyAsync(() -> "hello")
                .thenAccept((s) -> System.out.println(s + " world"));
    }

    private static void thenApply() {
        System.out.println("==============thenApply==================");
        String result = CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(s -> s + " world")
                .join();
        System.out.println(result);
    }
}
