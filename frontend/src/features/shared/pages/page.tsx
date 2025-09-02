import { useParams, Link } from "react-router-dom";

function Page() {
    const { pageId } = useParams<{ pageId: string }>();

    const [blocks, setBlocks] = useState([]);
}