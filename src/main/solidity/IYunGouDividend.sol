// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

interface IYunGouDividend {
    event Deposit(address indexed account, uint256 indexed amount);

    event Withdraw(
        uint256 indexed orderId,
        address indexed coinAddress,
        address indexed account,
        uint256 amount
    );

    function getOrderIdState(uint256 orderId) external view returns (bool);

    function getETHBalance() external view returns (uint256);

    function getERC20Balance(
        address coinAddress
    ) external view returns (uint256);

    function withdraw(
        bytes calldata data,
        bytes calldata signature
    ) external returns (bool);
}
