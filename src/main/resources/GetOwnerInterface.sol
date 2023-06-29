// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

interface GetOwnerInterface {
    //contract owner
    function owner() external view returns (address);

    // erc721 ownerOf
    function ownerOf(uint256 tokenId) external view returns (address owner);

    // erc1155 ownerOf
    function balanceOf(
        address account,
        uint256 id
    ) external view returns (uint256);
}
