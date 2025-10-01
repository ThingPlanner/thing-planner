import React from "react";


type AppTopbarProps = {
    children?: React.ReactNode;
}

export function AppTopbar({ children }: AppTopbarProps) {
    return (
        <nav className="flex items-center space-x-4 p-2 ">
            <div className="flex space-x-1">
                {children}
            </div>
        </nav>
    );
}
