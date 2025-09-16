import './App.css'
import Layout from './components/layout/Layout';
import {ThemeProvider} from "@/components/theme/theme-provider.tsx";
import {BrowserRouter} from "react-router-dom";
import {AppRoutes} from "@/routes/routes.tsx";

function App() {

  return (
    <ThemeProvider defaultTheme="system" storageKey="vite-ui-theme">
        <BrowserRouter>
            <Layout>
                <AppRoutes />
            </Layout>
        </BrowserRouter>
    </ThemeProvider>
  )
}

export default App
