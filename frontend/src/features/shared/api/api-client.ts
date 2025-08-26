/**
 * Reusable API client class for making HTTP requests.
 * ApiClient centralizes request, logic and error handling.
 */
export class ApiClient {
    private readonly baseUrl: string;

    /**
     * @param baseUrl The base URL for all API requests to ThingPlanner backend.
     */
    constructor(baseUrl: string) {
        this.baseUrl = baseUrl;
    }

    /**
     * Helper function to handle failed requests due to network or parsing errors.
     * @param response The Response object from the fetch call.
     */
    private async _handleFailedRequest(response: Response): Promise<void> {
        if (!response.ok) {
            const errorText = await response.text();
            console.error(`Request failed: ${response.status} ${response.statusText}`, errorText);
            throw new Error(`Request failed with status ${response.status}`);
        }
    }

    /**
     * Async method to handle GET requests.
     * @param endpoint API Endpoint.
     * @returns A Promise that resolved with the parsed JSON.
     */
    public async get<R = unknown>(endpoint: string): Promise<R> {
        const url = `${this.baseUrl}/${endpoint}`;
        const options: RequestInit = { method: "GET" };

        const response = await fetch(url, options);
        await this._handleFailedRequest(response);

        return await response.json();
    }

    /**
     * Async method to handle POST requests.
     * @param endpoint API endpoint.
     * @param payload Data payload object.
     * @returns A Promise that resolved with the parse JSON response.
     */
    public async post<T, R = unknown>(endpoint: string, payload: T): Promise<R> {
        const url = `${this.baseUrl}/${endpoint}`;
        const options: RequestInit = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        };

        const response = await fetch (url, options);
        await this._handleFailedRequest(response);

        if (response.status === 204) {
            return undefined as R;
        }

        return await response.json();
    }

    /**
     * Async method to handle DELETE requests.
     * @param endpoint API endpoint.
     */
    public async delete(endpoint: string): Promise<void> {
        const url = `${this.baseUrl}/${endpoint}`;
        const options: RequestInit = { method: "DELETE" };

        const response = await fetch(url, options);
        await this._handleFailedRequest(response);
    }
}