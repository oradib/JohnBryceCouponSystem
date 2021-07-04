export class User {
  public constructor(
    public clientType?: string,
    public email?: string,
    public password?: string,
    public token: string = ''
  ) {}
}
