// SPDX-License-Identifier: MIT
pragma solidity ^0.7.0;

abstract contract YgmeStakingDomain {
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
}

contract YgmeStaking is YgmeStakingDomain {
    uint64 public constant ONE_CYCLE = 1 days;

    uint64[3] private stakingPeriods;

    address private withdrawSigner;

    mapping(uint256 => StakingData) public stakingDatas;

    mapping(address => uint256[]) private stakingTokenIds;

    mapping(uint256 => bool) public orderIsInvalid;

    uint128 public accountTotal;

    uint128 public ygmeTotal;

    constructor(address _ygme, address _ygio, address _withdrawSigner) {}

    function setPause() external {}

    function setStakingPeriods(uint64[3] calldata _days) external {
        for (uint i = 0; i < _days.length; i++) {
            stakingPeriods[i] = _days[i] * ONE_CYCLE;
        }
    }

    function setWithdrawSigner(address _withdrawSigner) external {
        withdrawSigner = _withdrawSigner;
    }

    function getStakingTokenIds(
        address _account
    ) external view returns (uint256[] memory) {
        return stakingTokenIds[_account];
    }

    function getStakingPeriods() external view returns (uint64[3] memory) {
        return stakingPeriods;
    }

    function getWithdrawSigner() external view returns (address) {
        return withdrawSigner;
    }

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

    // TODO: data = abi.encode(orderId, account, amount)
    function withdrawERC20(
        bytes calldata data,
        bytes calldata signature
    ) external returns (bool) {
        return true;
    }

    function _verifySignature(
        bytes32 hash,
        bytes calldata signature
    ) internal view {}
}
