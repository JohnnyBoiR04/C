@SuppressWarnings("CheckReturnValue")
public class Execute extends HelloBaseVisitor<String> {

   @Override public String visitMain(HelloParser.MainContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitGreetings(HelloParser.GreetingsContext ctx) {
      String id = ctx.ID().getText();
      System.out.println("olá " + id);
      return null;
   }

   @Override public String visitGoodbye(HelloParser.GoodbyeContext ctx) {
      String id = ctx.ID().getText();
      System.out.println("adeus " + id);
      return null;
   }
}
