// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

// pragma experimental ABIEncoderV2;

interface YunGouInterface {
    enum OrderType {
        ETH_TO_ERC721,
        ETH_TO_ERC1155,
        ERC20_TO_ERC721,
        ERC20_TO_ERC1155,
        ERC721_TO_ERC20,
        ERC1155_TO_ERC20
    }

    // buy it now
    struct BasicOrderParameters {
        OrderType orderType;
        address payable offerer;
        address offerToken;
        uint256 offerTokenId;
        uint256 unitPrice;
        uint256 sellAmount;
        uint256 startTime;
        uint256 endTime;
        address paymentToken;
        uint256 paymentTokenId;
        uint256 salt;
        uint256 royaltyFee;
        uint256 platformFee;
        uint256 afterTaxPrice;
    }

    struct BasicOrder {
        BasicOrderParameters parameters;
        bytes orderSignature;
        uint256 buyAmount;
        uint256 totalRoyaltyFee;
        uint256 totalPlatformFee;
        uint256 totalAfterTaxIncome;
        uint256 totalPayment;
        uint256 expiryDate;
        bytes systemSignature;
    }

    event Exchange(
        address indexed offerer,
        address indexed offerToken,
        uint256 indexed offerTokenId,
        address buyer,
        uint256 buyAmount,
        uint256 totalPayment,
        uint256 totalRoyaltyFee,
        uint256 totalPlatformFee
    );

    function setBeneficiary(address payable newBeneficiary) external;

    function setSystemVerifier(address _systemVerifier) external;

    function getBeneficiary() external view returns (address);

    function getSystemVerifier() external view returns (address);

    function setPause() external;

    function excuteWithETH(
        BasicOrder calldata order,
        address receiver
    ) external payable returns (bool);

    function batchExcuteWithETH(
        BasicOrder[] calldata orders,
        address receiver
    ) external payable returns (bool);

    function name() external pure returns (string memory contractName);

    function information()
        external
        view
        returns (string memory version, bytes32 domainSeparator);

    function getOrderHash(
        BasicOrderParameters calldata orderParameters
    ) external view returns (bytes32 orderHash);

    // function demo(uint256[][] calldata x) external;
}
