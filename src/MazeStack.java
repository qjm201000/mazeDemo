import java.util.Arrays;
public class MazeStack {
    private MazeNode[] stack;
    private int top;

    public MazeStack(){
        this.stack = new MazeNode[10];
        this.top = 0;
    }
    /**
     * 入栈
     */
    public void push(MazeNode mazeNode) {
        if(full()) {
            this.stack=Arrays.copyOf(this.stack, 2*this.top);
        }
        this.stack[this.top]=mazeNode;
        this.top++;
    }
    /**
     * 出栈
     */
    public void pop() {
        if(empty()) {
            return;
        }
        this.top--;
    }

    /**
     *  返回栈顶元素
     * @return
     */
    public MazeNode top() {
        return this.stack[this.top-1];
    }

    /**
     * 判断栈空
     * @return
     */
    public boolean empty() {
        return this.top == 0;
    }
    /**
     * 判断栈满
     * @return
     */
    public boolean full() {
        return this.top == this.stack.length;
    }
}
