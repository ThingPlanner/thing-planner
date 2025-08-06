import { Router } from "https://deno.land/x/oak@v17.1.5/router.ts";
import { login, callback, protectedRoute} from "../controllers/authController.ts";

const authRouter = new Router();

authRouter
    .get("/login", login)
    .get("/callback", callback);

export default authRouter;