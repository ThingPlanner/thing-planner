import { Routes, Route } from "react-router-dom";
import {Page} from "@/features/shared/pages/page.tsx";

export const AppRoutes = () => {
    return (
        <Routes>
            <Route path ="/page/:pageId" element={<Page />} />
            <Route path ="/" element={<Page />} />
        </Routes>
    );
}