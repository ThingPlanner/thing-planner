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
        return this.dirs;
    }

    public traverseUp(targetId: string): DirStack[] {
        if (this.cur.parent && this.cur.parent.id === targetId) {
            this.cur = this.cur.parent;
            this.dirs.pop();
        }
        return this.dirs;
    }


    private buildTree(raw: DirNodeJson, parent: DirNode | null = null): DirNode {
        const node: DirNode = {
            id: raw.id,
            route: raw.route,
            isBottom: raw.isBottom,
            parent,
            children: [],
        };

        node.children = (raw.children ?? []).map(child => this.buildTree(child, node));

        return node;
    }

    public getBreadcrumbPath(): DirStack[] {
        const path: DirStack[] = [];
        let node: DirNode | null = this.cur;

        while (node) {
            path.unshift({ node });
            node = node.parent;
        }

        return path;
    }
}
