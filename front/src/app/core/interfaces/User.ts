export interface UserResponse {
  _embedded: {
    userList: [{
      id?: number;
      firstName: string;
      lastName: string;
      email: string;
      password: string;
      profile: string;
      role: string;
      _links: {
        users: {
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


export interface User {
  id?: number;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  role: string;
  profile?: string;
  _links?: {
    users: {
      href: string;
    },
    self: {
      href: string;
    }
  }
}
