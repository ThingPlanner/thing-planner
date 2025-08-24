/**
 * Reusable API client class for making HTTP requests.
 * ApiClient centralizes request, logic and error handling.
 */
class ApiClient {
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
}