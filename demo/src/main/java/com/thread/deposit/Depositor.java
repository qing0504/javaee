package com.thread.deposit;

public class Depositor {
	public static void main(String[] args) {
        Account account = new Account();
        new Thread(new DrawMethod(account, 100), "- 取钱者").start();
        new Thread(new DepositMethod(account, 100), "+ 存钱者").start();
    }

    private static class DrawMethod implements Runnable {

        private Account account;
        private double amount;

        public DrawMethod(Account account, double amount) {
            this.account = account;
            this.amount = amount;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    account.draw(amount);
                    SleepUtil.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static class DepositMethod implements Runnable {

        private Account account;
        private double amount;

        public DepositMethod(Account account, double amount) {
            this.account = account;
            this.amount = amount;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    SleepUtil.sleep(500);
                    account.deposit(amount);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
