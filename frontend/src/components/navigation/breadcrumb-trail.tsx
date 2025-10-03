import type { PageMetadata } from "@/features/pages/types/page-types.ts";
import {
    Breadcrumb,
    BreadcrumbList,
    BreadcrumbSeparator,
    BreadcrumbItem
} from "@/components/ui/breadcrumb.tsx";
import { Link } from "react-router-dom";
import {useBreadcrumb} from "@/features/pages/use-case/get-breadcrumb.ts";

type BreadcrumbProps = {
    pages: PageMetadata[];
};

export function BreadcrumbTrail({ pages }: BreadcrumbProps) {
    return (
        <Breadcrumb>
            <BreadcrumbList>
                {pages.map((page: PageMetadata, idx: number) => (
                    <BreadcrumbItem key={page.id}>
                    <Link to={`/page/${page.id}`} className={"cursor-pointer"}>
                        {page.title}
                    </Link>
                    {idx < pages.length - 1 && <BreadcrumbSeparator />}
                    </BreadcrumbItem>
                ))}
            </BreadcrumbList>
        </Breadcrumb>
    );
}

export function BreadcrumbContainer({ pageId }: { pageId: string }) {
    const { breadcrumb, loading, error } = useBreadcrumb(pageId);

    if (loading) return <p>Loading breadcrumb...</p>;
    if (error) return <p style={{ color: "red" }}>{error}</p>;

    return <BreadcrumbTrail pages={breadcrumb} />;
}