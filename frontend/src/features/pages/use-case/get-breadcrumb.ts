import {React, useEffect, useState} from 'react';
import { PageMetadata } from "@/features/pages/types/page-types.ts";
import { ApiClient } from "@/features/shared/api/api-client.ts";

const apiClient = new ApiClient("http://localhost:8080/");

export function useBreadcrumb(pageId: string) {
    const [breadcrumb, setBreadcrumb] = useState<PageMetadata[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        if (!pageId) {
            return;
        }

        let isMounted = true;

        const load = async () => {
            try {
                setLoading(true);
                const breadcrumbData = await fetchBreadcrumb(pageId);
                setBreadcrumb(breadcrumbData);
            } catch (err: any) {
                setError(err.message ?? "Failed to load breadcrumb trail.");
            } finally {
                if (isMounted) {
                    setLoading(false);
                }
            }
        };

        load().catch(console.error);

        return () => {
            isMounted = false;
        }
    }, [pageId]);

    return { breadcrumb, loading, error };
}

export async function fetchBreadcrumb(pageId: string): Promise<PageMetadata[]> {
    return apiClient.get<PageMetadata[]>(`/pages/breadcrumb/get/${pageId}`);
}