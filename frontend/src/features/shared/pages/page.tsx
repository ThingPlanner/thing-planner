import { Container } from "@/components/layout/container/container";
import {useParams, Link, Route, useNavigate} from "react-router-dom";
import {BreadcrumbTrail} from "@/components/navigation/breadcrumb-trail.tsx";
import {useState} from "react";
import { useBreadcrumb } from "@/features/pages/use-case/get-breadcrumb.ts";

export function Page() {
    const [blocks, setBlocks] = useState([]);
    const { pageId } = useParams<{ pageId: string }>();
    const { breadcrumb, loading } = useBreadcrumb(pageId!);
    const navigate = useNavigate();

    if (loading) return <div>Loading...</div>;

    return (
        <Container>
            <BreadcrumbTrail
                pages={breadcrumb}
                onNavigate={(url) => {
                    navigate(url);
                }}
            />
            <p>Hello</p>
            <p>This is a page: {pageId}</p>
        </Container>
    );
}