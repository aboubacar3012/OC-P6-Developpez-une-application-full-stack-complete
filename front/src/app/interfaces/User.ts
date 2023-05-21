export interface UserResponse {
  _embedded: {
    userList: [{
      id?: number;
      username: string
      email: string
      password: string
      profile: string
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
  id?:number;
  username: string;
  email: string;
  password: string;
  profile?:string;
  _links?: {
    users: {
      href: string;
    },
    self: {
      href: string;
    }
  }
}
