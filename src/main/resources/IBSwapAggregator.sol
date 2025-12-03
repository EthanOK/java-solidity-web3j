// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

struct SwapData {
    SwapType swapType;
    address extRouter;
    bytes extCalldata;
    bool needScale;
}

struct SwapDataExtra {
    address tokenIn;
    address tokenOut;
    uint256 minOut;
    SwapData swapData;
}

struct SwapDataInfo {
    address tokenIn;
    uint256 amountIn;
    address tokenOut;
    uint256 minOut;
    address receiver;
    SwapData swapData;
}

enum SwapType {
    NONE,
    KYBERSWAP,
    ODOS,
    // ETH_WETH not used in Aggregator
    ETH_WETH,
    OKX,
    ONE_INCH,
    PARASWAP,
    RESERVE_2,
    RESERVE_3,
    RESERVE_4,
    RESERVE_5
}

error InsufficientAmountOut(uint256 actualAmountOut, uint256 minAmountOut);

interface IBSwapAggregator {
    event SwapSingle(
        SwapType indexed swapType,
        address indexed tokenIn,
        uint256 amountIn
    );

    event SwapExactEvent(
        SwapType indexed swapType,
        address sender,
        address indexed tokenIn,
        uint256 amountIn,
        address receiver,
        address indexed tokenOut,
        uint256 amountOut
    );

    function swap(
        address tokenIn,
        uint256 amountIn,
        SwapData calldata swapData
    ) external payable;

    function swapExactInput(
        SwapDataInfo calldata swapDataInfo
    ) external payable returns (uint256 amountOut);

    function swapExactInputWithPermit(
        SwapDataInfo calldata swapDataInfo,
        uint256 deadline,
        bytes memory signature
    ) external payable returns (uint256 amountOut);

    function swapExactInputWithPermit2(
        SwapDataInfo calldata swapDataInfo,
        uint256 nonce,
        uint256 deadline,
        bytes calldata signature
    ) external payable returns (uint256 amountOut);
}
