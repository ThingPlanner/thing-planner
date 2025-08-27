type DirNode = {
    name: string,
    children?: DirNode[],
    isBottom: boolean
};

class DirectoryPath {
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


