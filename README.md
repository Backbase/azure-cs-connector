<p align="center">
  <img width="120" src="Backbase.png?raw=true" alt="Backbase Logo">
</p>

<p align="center">
This service is used by the <a href="https://community.backbase.com/documentation/foundation_services/latest/message_delivery">Message Delivery</a> capability, and provides integration to Azure Communication Service for sending emails.
<p>

<p align="center"> 
    <a href="https://github.com/backbase/azure-cs-connector/actions/workflows/main.yml">
        <img src="https://github.com/backbase/azure-cs-connector/actions/workflows/main.yml/badge.svg" alt="Build" />
    </a>
    <a href="https://sonarcloud.io/summary/new_code?id=Backbase_azure-cs-connector">
        <img src="https://sonarcloud.io/api/project_badges/measure?project=Backbase_azure-cs-connector&metric=alert_status" alt="Quality Gate" />
    </a>
    <a href="https://sonarcloud.io/summary/new_code?id=Backbase_azure-cs-connector">
        <img src="https://sonarcloud.io/api/project_badges/measure?project=Backbase_azure-cs-connector&metric=sqale_rating" alt="Maintainability Rating" />
    </a>
    <a href="https://conventionalcommits.org">
        <img src="https://img.shields.io/badge/Conventional%20Commits-1.0.0-yellow.svg" alt="Conventional Commits" />
    </a>
</p>

---

## Table of contents
* [About](#about)
* [Usage](#usage)
* [Contributing](#contributing)
* [License](#license)

## About

The `azure-cs-connector` adds support for sending emails using [Azure Communication Service](https://azure.microsoft.com/en-us/products/communication-services/) it is used within the [Message Delivery](https://community.backbase.com/documentation/foundation_services/latest/message_delivery) capability.
In the below diagram you can see how it fits into the Backbase architecture.

This connector was created using the following guide [Build a custom channel](https://community.backbase.com/documentation/foundation_services/latest/build_custom_channel). We use the `communication-channel` building block as a baseline and implement the connector to support the `email` channel specification.

![Architecture](docs/azure-cs-connector.svg)

## Usage
This service is deployed like any other Backbase capability/service and ships with a Docker image.

The following properties **must** be set as they have no default:

Property | Description
--- | ---
**azure.emailConnectionString** | azure email communication service connection string
**backbase.mail.from-address** | senders email address
**backbase.mail.from-name** | senders name


## Contributing

First off, thanks for taking the time to contribute! Contributions are what makes the open-source community such an amazing place to learn, inspire, and create. Any contributions you make will benefit everybody else and are **greatly appreciated**.

Please adhere to this project's [code of conduct](CODE_OF_CONDUCT.md). For detailed instructions on repo organization, linting, testing, and other
steps see our [contributing guidelines](CONTRIBUTING.md)

#### Contributors

[![](https://contrib.rocks/image?repo=backbase/azure-cs-connector)](https://github.com/backbase/azure-cs-connector/graphs/contributors)

## License

This project is licensed under the **Backbase** license.

See [LICENSE.md](LICENSE.md) for more information.