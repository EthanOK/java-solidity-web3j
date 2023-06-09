// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract NFTExchangeLog {
    event Exchange(
        address indexed sellToken,
        uint256 indexed sellTokenId,
        uint256 sellAmount,
        uint256 unitPrice,
        address seller,
        address buyToken,
        uint256 buyTokenId,
        address buyer,
        uint256 amount,
        uint256 payPrice,
        uint256 royaltyFee
    );
}
