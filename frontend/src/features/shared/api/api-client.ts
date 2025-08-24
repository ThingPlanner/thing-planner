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
}