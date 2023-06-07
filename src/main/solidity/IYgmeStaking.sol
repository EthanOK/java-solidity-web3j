// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract IYgmeStaking {
    struct StakingData {
        address owner;
        bool stakedState;
        uint128 startTime;
        uint128 endTime;
    }

    event Staking(
        address indexed account,
        uint256 indexed tokenId,
        address indexed nftContract,
        uint256 startTime,
        uint256 endTime,
        uint256 pledgeType
    );

    event WithdrawERC20(uint256 orderId, address account, uint256 amount);

    uint64 public constant ONE_CYCLE = 1 days;

    uint64[3] private stakingPeriods;

    address public ygme;

    address public ygio;

    address private withdrawSigner;

    mapping(uint256 => StakingData) public stakingDatas;

    mapping(address => uint256[]) private stakingTokenIds;

    mapping(uint256 => bool) public orderIsInvalid;

    uint128 public accountTotal;

    uint128 public ygmeTotal;

    constructor(address _ygme, address _ygio, address _withdrawSigner) {}

    function setPause() external {}

    function setStakingPeriods(uint64[3] calldata _days) external {}

    function setWithdrawSigner(address _withdrawSigner) external {}

    function getStakingTokenIds(
        address _account
    ) external view returns (uint256[] memory) {}

    function getStakingPeriods() external view returns (uint64[3] memory) {}

    function getWithdrawSigner() external view returns (address) {}

    function staking(
        uint256[] calldata _tokenIds,
        uint256 _stakeDays
    ) external returns (bool) {
        return true;
    }

    function unStake(uint256[] calldata _tokenIds) external returns (bool) {
        return true;
    }

    function unStakeOnlyOwner(uint256[] calldata _tokenIds) external {}
}
