TODO
====

- A better error handling (see payload errors)
- Use User.id in token
- Payload and extractors roles ?
  - Payload is a simple bean.
  - Payload belongs to view module
  - Payload has some business methods (isValid, getErrors)
  - Payload is constructed by extractors
  - Extractors are parsing json
  - Extractors belongs to spark module
  - TODO :
    - Let payloads beeing simple beans
    - Move extractors where json is already used
    - Make business validator class (will also simplify routes impl)