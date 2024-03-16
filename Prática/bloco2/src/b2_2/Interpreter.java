@SuppressWarnings("CheckReturnValue")
public class Interpreter extends SuffixCalculatorBaseVisitor<String> {

   @Override public String visitProgram(SuffixCalculatorParser.ProgramContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitStat(SuffixCalculatorParser.StatContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExpr(SuffixCalculatorParser.ExprContext ctx) {
      if (ctx.Number() != null) {
         return ctx.Number().getText();
      }

      String left = visit(ctx.expr(0));
      String right = visit(ctx.expr(1));
      String op = ctx.op.getText();

      double leftNum = Double.parseDouble(left);
      double rightNum = Double.parseDouble(right);
      double result = 0;

      switch (op) {
         case "+":
            result = leftNum + rightNum;
            break;
         case "-":
            result = leftNum - rightNum;
            break;
         case "*":
            result = leftNum * rightNum;
            break;
         case "/":
            result = leftNum / rightNum;
            break;  
      }      

      System.out.println(result);
      return String.valueOf(result);
   }
}
