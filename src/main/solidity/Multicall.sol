// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract Multicall {
    struct Call {
        address target;
        bytes callData;
    }

    function aggregateStaticCall(
        Call[] calldata calls
    ) external view returns (uint256 blockNumber, bytes[] memory returnData) {}

    function aggregate(
        Call[] memory calls
    ) external returns (uint256 blockNumber, bytes[] memory returnData) {}
}
