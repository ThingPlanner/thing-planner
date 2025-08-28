type DirNode = {
    id: string,
    route: string,
    parent: DirNode | null,
    children: DirNode[],
    isBottom: boolean
};

type DirNodeJson = Omit<DirNode, "parent"> & { children?: DirNodeJson[] };

type DirStack = {
    node: DirNode
}

export class DirectoryPath {
    rootNode: DirNode;
    trail: DirNode[];
    cur: DirNode;
    dirs: DirStack[];

    constructor(pathData: any[]) {
        this.rootNode = this.buildTree(pathData, null);
        this.cur = this.rootNode;
        this.trail = [this.rootNode];
        this.dirs = [{ node: this.cur }];
    }

    public traverseDown(targetId: string): DirStack[] {
        if (!this.cur) return this.dirs;

        const child = this.cur.children.find(cld => cld.id === targetId);
        if (child) {
            this.cur = child;
            this.dirs.push({ node: child });
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

