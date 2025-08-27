export type DirNode = {
    name: string;
    children?: DirNode[];
    isFile: boolean;
}