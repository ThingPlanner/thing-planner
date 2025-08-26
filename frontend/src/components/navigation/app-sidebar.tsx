import {
    Sidebar,
    SidebarContent,
    SidebarFooter,
    SidebarGroup, SidebarGroupLabel,
    SidebarHeader,
} from "@/components/ui/sidebar"
import CreateEvent from "@/features/event/components/forms/create-event"

export function AppSidebar() {
    return (
        <Sidebar>
            <SidebarHeader>
                <h2>Header</h2>
            </SidebarHeader>
            <SidebarContent>
                <SidebarGroup>
                    <SidebarGroupLabel>Application</SidebarGroupLabel>
                    <CreateEvent />
                </SidebarGroup>
            </SidebarContent>
            <SidebarFooter>
                <p>Footer</p>
            </SidebarFooter>
        </Sidebar>
    );
}