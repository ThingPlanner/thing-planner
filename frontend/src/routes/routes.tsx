import { Routes, Route } from "react-router-dom";

export const AppRoutes = () => {
    return (
        <Routes>
            <Route path ="/page/:pageId" element{<Page />}
        </Routes>
    );
}