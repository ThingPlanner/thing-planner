import { Application } from "https://deno.land/x/oak/mod.ts";
import authRouter from "./src/auth/api/routes/authRouter.ts";

const app = new Application();

app.use(authRouter.routes());
app.use(authRouter.allowedMethods());

if (import.meta.main) {
  console.log("Listening on http://localhost:8081");
  await app.listen({ port: 8081 });
}