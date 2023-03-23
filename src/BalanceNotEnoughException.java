public class BalanceNotEnoughException extends Exception{
    //自定义余额不足异常，gap存储缺少多少钱      此异常为检查性异常，需要显示处理
    private double gap;

    public BalanceNotEnoughException(double gap) {
        this.gap = gap;
    }

    public double getGap() {
        return gap;
    }
}
