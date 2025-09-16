import React from "react";
import {DirectoryPath} from "@/utils/algorithms/search/directory-path.ts";
import type { PageBreadcrumb } from "@/features/pages/types/breadcrumb-types";
import {
    Breadcrumb,
    BreadcrumbList,
    BreadcrumbEllipsis,
    BreadcrumbLink,
    BreadcrumbSeparator,
    BreadcrumbItem
} from "@/components/ui/breadcrumb.tsx";
import { Link } from "react-router-dom";

type BreadcrumbProps = {
    pages: PageBreadcrumb[];
    onNavigate: (targetPath: string) => void;
};

export function BreadcrumbTrail({ pages }: BreadcrumbProps) {
    return (
        <Breadcrumb>
            <BreadcrumbList>
                {pages.map((page: PageBreadcrumb, idx: number) => (
                    <BreadcrumbItem key={page.id}>
                        <Link to={page.url} className="cursor-pointer">
                            {page.title}
                        </Link>
                        { idx < pages.length - 1 && <BreadcrumbSeparator /> }
                    </BreadcrumbItem>
                ))}
            </BreadcrumbList>
        </Breadcrumb>
    );
}
