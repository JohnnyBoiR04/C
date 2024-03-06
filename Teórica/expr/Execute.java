@SuppressWarnings("CheckReturnValue")
public class Execute extends exprBaseVisitor<String> {

   @Override public String visitMain(exprParser.MainContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitStat(exprParser.StatContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitAssignment(exprParser.AssignmentContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitExpression(exprParser.ExpressionContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }
}
