@SuppressWarnings("CheckReturnValue")
public class Interpreter extends PrefixCalculatorBaseVisitor<String> {

   @Override public String visitProgram(PrefixCalculatorParser.ProgramContext ctx) {
      String result = null;
      for (PrefixCalculatorParser.StatContext stat : ctx.stat()) {
         result = visit(stat);
      }
      System.out.println("Result: " + result);
      return result;
   }

   @Override public String visitStat(PrefixCalculatorParser.StatContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitExprMulDivMod(PrefixCalculatorParser.ExprMulDivModContext ctx) {
      int left = Integer.parseInt(visit(ctx.expr(0)));
      int right = Integer.parseInt(visit(ctx.expr(1)));
      int res = 0;

      switch (ctx.op.getText()) {
         case "*":
            res = left * right;            
            break;
         case "/":
            if (right == 0) throw new ArithmeticException("Division by zero");
            res = left / right;
            break;
         case "%":
            res = left % right;
            break;      
         default:
            throw new RuntimeException("Unknown operator: " + ctx.op.getText());
      }

      return String.valueOf(res);
   }

   @Override public String visitExprAddSub(PrefixCalculatorParser.ExprAddSubContext ctx) {
      int left = Integer.parseInt(visit(ctx.expr(0)));
      int right = Integer.parseInt(visit(ctx.expr(1)));
      int res = 0;
      switch (ctx.op.getText()) {
         case "+":
            res = left + right;
            break;
         case "-":
            res = left - right;
            break;
         default:
            throw new RuntimeException("Unknown operator: " + ctx.op.getText());
      }

      return String.valueOf(res);
   }

   @Override public String visitExprUnaryOp(PrefixCalculatorParser.ExprUnaryOpContext ctx) {
      int operand = Integer.parseInt(visit(ctx.expr()));
      if (ctx.op.getText() != null && ctx.op.getText().equals("-")) {
         operand = -operand;
      }

      return String.valueOf(operand);
   }

   @Override public String visitExprInteger(PrefixCalculatorParser.ExprIntegerContext ctx) {
      return ctx.Integer().getText();
   }

   @Override public String visitExprParent(PrefixCalculatorParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }
}
