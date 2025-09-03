import {
    NavigationMenu,
    NavigationMenuItem,
    NavigationMenuList,
    NavigationMenuTrigger
} from "@/components/ui/navigation-menu";
import React, { useState } from "react";
import { DirectoryPath } from "@/utils/algorithms/search/directory-path.ts";
import pathData from "./p.json";
import Breadcrumb from "@/components/navigation/breadcrumb-trail.tsx";
import type { DirNode } from "@/types/tree/tree.ts";
import { Button } from "../ui/button";
import BreadcrumbTrail from "@/components/navigation/breadcrumb-trail.tsx";

export function AppTopbar() {
    // Initialize the directory tree
    const [dir] = useState(new DirectoryPath(pathData));

    // For demonstration, let's render breadcrumbs
    const breadcrumb = dir.getBreadcrumbPath();

    return (
        <nav className="flex items-center space-x-4 p-2 ">
            <div className="flex space-x-1">

            </div>
        </nav>
    );
}
