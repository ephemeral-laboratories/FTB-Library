package com.feed_the_beast.ftbl.lib;

import com.feed_the_beast.ftbl.api.INotification;
import com.feed_the_beast.ftbl.api.gui.IDrawableObject;
import com.feed_the_beast.ftbl.lib.client.DrawableItem;
import com.feed_the_beast.ftbl.lib.client.ImageProvider;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

/**
 * @author LatvianModder
 */
public class Notification extends TextComponentString implements INotification
{
	public static Notification of(ResourceLocation id, String text, ITextComponent... lines)
	{
		Notification n = new Notification(id, text);

		for (ITextComponent line : lines)
		{
			n.addLine(line);
		}

		return n;
	}

	public static Notification of(ResourceLocation id, ITextComponent... lines)
	{
		return of(id, "", lines);
	}

	private final ResourceLocation id;
	private int timer;
	private IDrawableObject icon;

	private Notification(ResourceLocation i, String text)
	{
		super(text);
		id = i;
		timer = 60;
		icon = ImageProvider.NULL;
	}

	public Notification(INotification n)
	{
		this(n.getId(), n.getUnformattedComponentText());

		setStyle(n.getStyle().createShallowCopy());

		for (ITextComponent line : n.getSiblings())
		{
			getSiblings().add(line.createCopy());
		}

		setTimer(n.getTimer());
		setIcon(n.getIcon());
	}

	public Notification addLine(ITextComponent line)
	{
		if (!getSiblings().isEmpty())
		{
			appendText("\n");
		}

		appendSibling(line);
		return this;
	}

	public Notification setError()
	{
		getStyle().setColor(TextFormatting.DARK_RED);
		setIcon(new DrawableItem(new ItemStack(Blocks.BARRIER)));
		return this;
	}

	@Override
	public Notification appendText(String text)
	{
		return appendSibling(new TextComponentString(text));
	}

	@Override
	public Notification appendSibling(ITextComponent component)
	{
		super.appendSibling(component);
		return this;
	}

	public int hashCode()
	{
		int h = id.hashCode();

		return h;
	}

	public boolean equals(Object o)
	{
		return o == this || (o instanceof INotification && ((INotification) o).getId().equals(getId()));
	}

	public String toString()
	{
		return "Notification{id=" + id + ", siblings=" + siblings + ", style=" + getStyle() + ", timer=" + timer + ", icon=" + icon + '}';
	}

	@Override
	public ResourceLocation getId()
	{
		return id;
	}

	@Override
	public IDrawableObject getIcon()
	{
		return icon;
	}

	public Notification setIcon(IDrawableObject i)
	{
		icon = i;
		return this;
	}

	@Override
	public int getTimer()
	{
		return timer;
	}

	public Notification setTimer(int t)
	{
		timer = t;
		return this;
	}

	@Override
	public Notification createCopy()
	{
		return new Notification(this);
	}
}