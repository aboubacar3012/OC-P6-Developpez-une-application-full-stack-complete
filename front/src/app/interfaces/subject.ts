export interface SubjectResponse {
  _embedded: {
    subjectList: [{
      id: number;
      name: string;
      description: string;
      createdAt: string;
      subscriber_count: number;
      _links: {
        subjects: {
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
      href: string;
    }
  }
}

export interface Subject {
  id: number;
  name: string;
  description: string;
  createdAt: string;
  subscriber_count: number;
  _links: {
    subjects: {
      href: string;
    },
    self: {
      href: string;
    }
  }
}
