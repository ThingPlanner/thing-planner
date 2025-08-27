import {SidebarProvider, SidebarTrigger} from "@/components/ui/sidebar.tsx";
import {AppSidebar} from "@/components/navigation/app-sidebar.tsx";
import {AppTopbar} from "@/components/navigation/app-topbar.tsx";


export default function Layout({children}: {children: React.ReactNode}) {
    return (
        <SidebarProvider>
            <AppSidebar />

            <main className={"flex-1"}>
                <AppTopbar />
                <SidebarTrigger />
                {children}
            </main>
        </SidebarProvider>
    );
}