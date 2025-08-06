import {
    CLIENT_ID,
    CLIENT_SECRET,
    REDIRECT_URI,
    AUTH_URL,
    TOKEN_URL,
} from "./authConst.ts";

export function getLoginRedirectUrl(): string {
    const searchParams = new URLSearchParams({
        client_id: CLIENT_ID,
        redirect_uri: REDIRECT_URI,
        response_type: "code",
        scope: "openid profile email"
    });
    return `${AUTH_URL}?${searchParams.toString()}`;
}

export async function exchangeCodeForToken(code: string) {
    const body = new URLSearchParams({
        grant_type: "authorization_code",
        code,
        client_id: CLIENT_ID,
        //client_secret: CLIENT_SECRET,
        redirect_uri: REDIRECT_URI
    });

    const res = await fetch(TOKEN_URL, {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body,
        });

        if (!res.ok) {
            throw new Error("Failed to exchange code");
        }

        return await res.json();
}