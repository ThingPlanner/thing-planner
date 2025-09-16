import { useEffect, useState } from "react";
import type {PageBreadcrumb} from "@/features/pages/types/breadcrumb-types";
import {ApiClient} from "@/features/shared/api/api-client.ts";

export function useBreadcrumb(pageId: string) {
    const [breadcrumb, setBreadcrumb] = useState<PageBreadcrumb[]>([]);
    const [loading, setLoading] = useState(true);
    const apiClient = new ApiClient('http://localhost:8080/');


    useEffect(() => {
        if (pageId == null) {
            return;
        }

        const fetchBreadcrumb = async () => {
            try {
                const data: PageBreadcrumb[] = await apiClient.get<PageBreadcrumb[]>(`pages/breadcrumb/get/${pageId}`);
                setBreadcrumb(data);
            } catch (err) {
                console.error("Failed to fetch breadcrumb", err);
            } finally {
                setLoading(false);
            }
        };

        fetchBreadcrumb();
    }, [pageId]);

    return { breadcrumb, loading }
}