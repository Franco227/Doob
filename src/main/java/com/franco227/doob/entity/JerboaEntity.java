package com.franco227.doob.entity;

import com.franco227.doob.sound.DoobSounds;
import com.franco227.doob.tag.DoobTags;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.JumpControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class JerboaEntity extends AnimalEntity {
    private int jumpTicks;
    private int jumpDuration;
    private boolean lastOnGround;
    private int ticksUntilJump;
    public final AnimationState jumpAnimationState = new AnimationState();
    private int jumpAnimationProgress = 0;
    private boolean isJumping = false;

    public JerboaEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.jumpControl = new JerboaEntity.JerboaJumpControl(this);
        this.moveControl = new JerboaEntity.JerboaMoveControl(this);
        this.setSpeed(0.0);
    }

    public static DefaultAttributeContainer.Builder createJerboaAttributes() {
        return MobEntity.createMobAttributes()
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 5)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f);
    }

    private void setupAnimationStates() {
        if (!this.isOnGround()) {
            if (this.jumpAnimationProgress == 0) {
                this.jumpAnimationState.start(this.age);
                this.isJumping = true;
            }
        }
        if (this.isJumping) {
            this.jumpAnimationProgress++;
        }

        if (this.jumpAnimationProgress >= 10) {
            this.jumpAnimationState.stop();
            this.jumpAnimationProgress = 0;
            this.isJumping = false;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()) {
            this.setupAnimationStates();
        }
    }

    public static boolean isValidSpawn(EntityType<? extends JerboaEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isIn(DoobTags.JERBOA_SPAWNABLE_ON);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(DoobTags.JERBOA_FOOD);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return DoobEntities.JERBOA.create(world);
    }


    @Override
    protected SoundEvent getAmbientSound() {
        return DoobSounds.ENTITY_JERBOA_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return DoobSounds.ENTITY_JERBOA_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return DoobSounds.ENTITY_JERBOA_HURT;
    }

    protected SoundEvent getJumpSound() {
        return DoobSounds.ENTITY_JERBOA_JUMP;
    }


    protected boolean isJumpNeeded(final double targetY) {
        return targetY > this.getY() + 0.5;
    }

    @Override
    protected float getJumpVelocity() {
        float f;
        if (this.horizontalCollision || this.moveControl.isMoving() && this.isJumpNeeded(this.moveControl.getTargetY())) {
            f = 0.5F;
        } else {
            Path path = this.navigation.getCurrentPath();
            if (path != null && !path.isFinished() && this.isJumpNeeded(path.getNodePosition(this).y)) {
                f = 0.5F;
            } else {
                f = this.moveControl.getSpeed() <= 0.6 ? 0.2f : 0.3f;
            }
        }

        return super.getJumpVelocity(f / 0.42F);
    }

    @Override
    public void jump() {
        super.jump();
        double d = this.moveControl.getSpeed();
        if (d > 0.0) {
            double e = this.getVelocity().horizontalLengthSquared();
            if (e < 0.01) {
                this.updateVelocity(0.1F, new Vec3d(0.0, 0.0, 1.0));
            }
        }

        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_SPRINTING_PARTICLES_OR_RESET_SPAWNER_MINECART_SPAWN_DELAY);
        }
    }

    public void setSpeed(double speed) {
        if (this.getNavigation().speed != speed) {
            this.getNavigation().setSpeed(speed);
            this.moveControl.moveTo(this.moveControl.getTargetX(), this.moveControl.getTargetY(), this.moveControl.getTargetZ(), speed);
        }
    }

    @Override
    public void setJumping(boolean jumping) {
        super.setJumping(jumping);
        if (jumping) {
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
        }
    }

    public void startJump() {
        this.setJumping(true);
        this.jumpDuration = 10;
        this.jumpTicks = 0;
    }


    @Override
    public void mobTick() {
        if (this.ticksUntilJump > 0) {
            this.ticksUntilJump--;
        }

        if (this.isOnGround()) {
            if (!this.lastOnGround) {
                this.setJumping(false);
                this.scheduleJump();
            }

            JerboaEntity.JerboaJumpControl jerboaJumpControl = (JerboaEntity.JerboaJumpControl)this.jumpControl;
            if (!jerboaJumpControl.isActive()) {
                if (this.moveControl.isMoving() && this.ticksUntilJump == 0) {
                    Path path = this.navigation.getCurrentPath();
                    Vec3d vec3d = new Vec3d(this.moveControl.getTargetX(), this.moveControl.getTargetY(), this.moveControl.getTargetZ());
                    if (path != null && !path.isFinished()) {
                        vec3d = path.getNodePosition(this);
                    }

                    this.lookTowards(vec3d.x, vec3d.z);
                    this.startJump();
                }
            } else if (!jerboaJumpControl.canJump()) {
                this.enableJump();
            }
        }

        this.lastOnGround = this.isOnGround();
    }

    @Override
    public boolean shouldSpawnSprintingParticles() {
        return false;
    }

    private void lookTowards(double x, double z) {
        this.setYaw((float)(MathHelper.atan2(z - this.getZ(), x - this.getX()) * 180.0F / (float)Math.PI) - 90.0F);
    }

    private void enableJump() {
        ((JerboaEntity.JerboaJumpControl)this.jumpControl).setCanJump(true);
    }

    private void disableJump() {
        ((JerboaEntity.JerboaJumpControl)this.jumpControl).setCanJump(false);
    }

    private void doScheduleJump() {
        this.ticksUntilJump = this.moveControl.getSpeed() < 2.2 ? 10 : 1;
    }

    private void scheduleJump() {
        this.doScheduleJump();
        this.disableJump();
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (this.jumpTicks != this.jumpDuration) {
            this.jumpTicks++;
        } else if (this.jumpDuration != 0) {
            this.jumpTicks = 0;
            this.jumpDuration = 0;
            this.setJumping(false);
        }
    }


    public static class JerboaJumpControl extends JumpControl {
        private final JerboaEntity jerboa;
        private boolean canJump;

        public JerboaJumpControl(JerboaEntity jerboa) {
            super(jerboa);
            this.jerboa = jerboa;
        }

        public boolean isActive() {
            return this.active;
        }

        public boolean canJump() {
            return this.canJump;
        }

        public void setCanJump(boolean canJump) {
            this.canJump = canJump;
        }

        @Override
        public void tick() {
            if (this.active) {
                this.jerboa.startJump();
                this.active = false;
            }
        }
    }


    static class JerboaMoveControl extends MoveControl {
        private final JerboaEntity jerboa;
        private double jerboaSpeed;

        public JerboaMoveControl(JerboaEntity owner) {
            super(owner);
            this.jerboa = owner;
        }

        @Override
        public void tick() {
            if (this.jerboa.isOnGround() && !this.jerboa.jumping && !((JerboaEntity.JerboaJumpControl)this.jerboa.jumpControl).isActive()) {
                this.jerboa.setSpeed(0.0);
            } else if (this.isMoving() || this.state == State.JUMPING) {
                this.jerboa.setSpeed(this.jerboaSpeed);
            }

            super.tick();
        }

        @Override
        public void moveTo(double x, double y, double z, double speed) {
            if (this.jerboa.isTouchingWater()) {
                speed = 1.5;
            }

            super.moveTo(x, y, z, speed);
            if (speed > 0.0) {
                this.jerboaSpeed = speed;
            }
        }
    }

    // Goals
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(1, new PowderSnowJumpGoal(this, this.getWorld()));
        this.goalSelector.add(1, new JerboaEntity.EscapeDangerGoal(this, 2.2));
        this.goalSelector.add(2, new AnimalMateGoal(this, 0.8));
        this.goalSelector.add(3, new TemptGoal(this, 1.0, stack -> stack.isIn(DoobTags.JERBOA_FOOD), false));
        this.goalSelector.add(4, new JerboaEntity.FleeGoal<>(this, PlayerEntity.class, 8.0F, 2.2, 2.2));
        this.goalSelector.add(4, new JerboaEntity.FleeGoal<>(this, FoxEntity.class, 8.0F, 2.2, 2.2));
        this.goalSelector.add(4, new JerboaEntity.FleeGoal<>(this, CatEntity.class, 8.0F, 2.2, 2.2));
        this.goalSelector.add(4, new JerboaEntity.FleeGoal<>(this, OcelotEntity.class, 8.0F, 2.2, 2.2));
        this.goalSelector.add(4, new JerboaEntity.FleeGoal<>(this, HostileEntity.class, 4.0F, 2.2, 2.2));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 0.6));
        this.goalSelector.add(11, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(12, new LookAroundGoal(this));
    }

    static class EscapeDangerGoal extends net.minecraft.entity.ai.goal.EscapeDangerGoal {
        private final JerboaEntity jerboa;

        public EscapeDangerGoal(JerboaEntity jerboa, double speed) {
            super(jerboa, speed);
            this.jerboa = jerboa;
        }

        @Override
        public void tick() {
            super.tick();
            this.jerboa.setSpeed(this.speed);
        }
    }

    static class FleeGoal<T extends LivingEntity> extends FleeEntityGoal<T> {
        public FleeGoal(JerboaEntity jerboa, Class<T> fleeFromType, float distance, double slowSpeed, double fastSpeed) {
            super(jerboa, fleeFromType, distance, slowSpeed, fastSpeed);
        }

        @Override
        public boolean canStart() {
            return super.canStart();
        }
    }
}
