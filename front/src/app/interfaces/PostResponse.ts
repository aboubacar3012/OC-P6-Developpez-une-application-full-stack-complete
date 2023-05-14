export interface PostResponse{
  _embedded:{
    postList:[{
      id?: number;
      title: string;
      content: string;
      authorId: number;
      subjectId: number;
      createdAt?: string;
      _links: {
        posts:{
          href: string;
        },
        self: {
          href: string;
        }
      }
    }]
  },
  _links: {
    self: {
      href:string;
    }
  }
}
