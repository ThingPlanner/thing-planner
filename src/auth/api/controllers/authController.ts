import { Context } from "https://deno.land/x/oak@v17.1.5/context.ts";
import { getLoginRedirectUrl, exchangeCodeForToken} from "../../domain/services/authService.ts";

export function login (ctx: Context) {
    ctx.response.redirect(getLoginRedirectUrl());
    console.log(getLoginRedirectUrl())
}

export async function callback (ctx: Context) {
    const code = ctx.request.url.searchParams.get("code");

    if (!code) {
        ctx.response.status = 400;
        ctx.response.body = "Missing authorization code.";
        return;
    }

    try {
        const tokenData = await exchangeCodeForToken(code);
        await ctx.cookies.set("id_token", tokenData.id_token, { httpOnly: true });
        ctx.response.body = "Login successful!";
    } catch (err) {
        ctx.response.status = 500;
        ctx.response.body = "Authentication failed";
        console.log("Authentication failed,+", err);
    }
}

export async function protectedRoute(ctx: Context) {
    const token = await ctx.cookies.get("id_token");

    if (!token) {
        ctx.response.status = 401;
        ctx.response.body = "Not authenticated.";
        return;
    }
    ctx.response.body = "Protected resource!";
}