// SPDX-License-Identifier: MIT
pragma solidity ^0.7.0;
pragma experimental ABIEncoderV2;

interface IYgmeStaking {
    struct StakingData {
        address owner;
        bool stakedState;
        uint128 startTime;
        uint128 endTime;
    }

    // Multi Call
    struct Call {
        address target;
        bytes callData;
    }

    event Staking(
        address indexed account,
        uint256 indexed tokenId,
        address indexed nftContract,
        uint256 startTime,
        uint256 endTime,
        uint256 pledgeType
    );

    function orderIsInvalid(uint256 orderId) external view returns (bool);

    event WithdrawERC20(uint256 orderId, address account, uint256 amount);

    function setPause() external;

    function setStakingPeriods(uint64[3] calldata _periods) external;

    function setOperator(address _account) external;

    function setWithdrawSigner(address _withdrawSigner) external;

    function getStakingTokenIds(
        address _account
    ) external view returns (uint256[] memory);

    function getStakingPeriods() external view returns (uint64[3] memory);

    function getWithdrawSigner() external view returns (address);

    function staking(
        uint256[] calldata _tokenIds,
        uint256 _stakeTime
    ) external returns (bool);

    function unStake(uint256[] calldata _tokenIds) external returns (bool);

    // TODO: data = abi.encode(orderId, account, amount)
    function withdrawERC20(
        bytes calldata data,
        bytes calldata signature
    ) external returns (bool);

    function aggregateStaticCall(
        Call[] calldata calls
    ) external view returns (uint256 blockNumber, bytes[] memory returnData);
}
