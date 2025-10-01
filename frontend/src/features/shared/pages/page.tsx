import { Container } from "@/components/layout/container/container";
import { useParams } from "react-router-dom";
import {useEffect, useState} from "react";
import { BreadcrumbContainer } from "@/components/navigation/breadcrumb-trail.tsx";
import type {PageMetadata} from "@/features/pages/types/page-types.ts";
import {ApiClient} from "@/features/shared/api/api-client.ts";
import {AppTopbar} from "@/components/navigation/app-topbar.tsx";

const apiClient = new ApiClient("http://localhost:8080");

export function Page() {
    const { pageId } = useParams<{ pageId: string }>();
    const [page, setPage] = useState<PageMetadata | null>(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        if (!pageId) {
            return;
        }

        let isMounted = true;

        async function loadPage() {
            try {
                setLoading(true);
                const data = await apiClient.get<PageMetadata>(`/pages/get/${pageId}`);
                if(isMounted) {
                    setPage(data);
                }
            } catch (err: any) {
                setError(err.message ?? "Failed to load page");
            } finally {
                if (isMounted) {
                    setLoading(false);
                }
            }
        }

        loadPage().catch(console.error);

        return () => {
            isMounted = false;
        };
    }, [pageId]);

    if (loading) return <p>Loading page...</p>;
    if (error) return <p style={{ color: "red" }}>{error}</p>;
    if (!page) return <p>Page not found</p>;

    return (
        <Container>
            <AppTopbar>
                <BreadcrumbContainer pageId={pageId} />
            </AppTopbar>
            <Container>
                <h1>{page.title}</h1>
                <p>Page ID: {page.id}</p>
            </Container>
        </Container>
    );
}