// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

interface MinePoolInterface {
    function getMineOwner(uint256 _poolId) external view returns (address);

    function getOrderState(uint256 _orderId) external view returns (bool);
}
