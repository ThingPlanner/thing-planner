export type PageMetadata = {
    id: string;
    title: string;
    organization: string;
    thing: string;
    parent: string | null;
}

export type PageLayoutData = {
    id: string;
    //blocks: Block[];
}

//export type Block = {
//    blockPositions: Array[x, y];
//}