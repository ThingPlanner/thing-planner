import './App.css'
import Layout from './components/layout/Layout';
import CreateEvent from "/home/oliver/Development/ThingPlanner/thing-planner/frontend/src/features/event/components/forms/create-event.tsx";
import {ThemeProvider} from "@/components/theme/theme-provider.tsx";

function App() {

  return (
    <ThemeProvider defaultTheme="system" storageKey="vite-ui-theme">
        <Layout>
            <p>Hello</p>
        </Layout>
    </ThemeProvider>
  )
}

export default App
