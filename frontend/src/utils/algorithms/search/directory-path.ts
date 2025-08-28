type DirNode = {
    id: string,
    route: string,
    parent: DirNode | null,
    children: DirNode[],
    isBottom: boolean
};

class DirectoryPath {
type DirNodeJson = Omit<DirNode, "parent"> & { children?: DirNodeJson[] };

type DirStack = {
    node: DirNode
}

    rootNode: DirNode;
    dirPath: DirNode[];
    cur: DirNode;

    constructor(rootNode: DirNode) {
        this.rootNode = rootNode;
        this.dirPath = [rootNode];

        if (this.dirPath.length > 1) {
            this.cur = this.dirPath[this.dirPath.length - 1]
        } else {
            this.cur = rootNode;
        }
    }

    public traverseDown(targetNodeName: string) {
        this.cur.children?.forEach((child) => {
            if (child.name === targetNodeName) {
                this.dirPath.push(child);
            }
        });
    }

    public traverseUp(targetNodeName: string) {
        if (this.dirPath.length === 0 || this.cur.name === targetNodeName) {
            return;
        }

        this.dirPath.pop();
        this.cur = this.dirPath[this.dirPath.length - 1];

        this.traverseUp(targetNodeName);
    }
}

